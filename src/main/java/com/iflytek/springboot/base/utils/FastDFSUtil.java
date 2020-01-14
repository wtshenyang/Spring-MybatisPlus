package com.iflytek.springboot.base.utils;


import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FastDFSUtil {
    private TrackerClient trackerClient = null;
    private TrackerServer trackerServer = null;
    private StorageServer storageServer = null;
    private StorageClient1 storageClient = null;
    public FastDFSUtil() throws Exception {

        ClassPathResource cpr=new ClassPathResource("fdfs_client.conf");
        String path= cpr.getClassLoader().getResource("fdfs_client.conf").toURI().getPath();
        ClientGlobal.init(path);

        trackerClient = new TrackerClient();
        trackerServer = trackerClient.getConnection();
        storageServer = null;
        storageClient = new StorageClient1(trackerServer, storageServer);
    }

    /**
     * 上传文件方法
     * <p>Title: uploadFile</p>
     * <p>Description: </p>
     * @param fileName 文件全路径
     * @param extName 文件扩展名，不包含（.）
     * @param metas 文件扩展信息
     * @return
     * @throws Exception
     */
    public String uploadFile(String fileName, String extName, NameValuePair[] metas) throws Exception {
        String result = storageClient.upload_file1(fileName, extName, metas);
        return result;
    }

    public String uploadFile(String fileName) throws Exception {
        return uploadFile(fileName, null, null);
    }

    public String uploadFile(String fileName, String extName) throws Exception {
        return uploadFile(fileName, extName, null);
    }

    /**
     * 上传文件方法
     * <p>Title: uploadFile</p>
     * <p>Description: </p>
     * @param fileContent 文件的内容，字节数组
     * @param extName 文件扩展名
     * @param metas 文件扩展信息
     * @return
     * @throws Exception
     */
    public String uploadFile(byte[] fileContent, String extName, NameValuePair[] metas) throws Exception {

        String result = storageClient.upload_file1(fileContent, extName, metas);
        return result;
    }

    public String uploadFile(byte[] fileContent) throws Exception {
        return uploadFile(fileContent, null, null);
    }

    public String uploadFile(byte[] fileContent, String extName) throws Exception {
        return uploadFile(fileContent, extName, null);
    }

    public String uploadToBase64File(String baseresult, String extName) throws Exception {
        BASE64Decoder decoder = new BASE64Decoder();
        // Base64解码
        byte[] b = decoder.decodeBuffer(baseresult);
        for (int i = 0; i < b.length; ++i) {
            if (b[i] < 0) {// 调整异常数据
                b[i] += 256;
            }
        }

        return uploadFile(b, extName, null);
    }
    public void testFastDfsClient() throws Exception {
        FastDFSUtil fastDFSClient = new FastDFSUtil();
        String file = fastDFSClient.uploadFile("D:/Documents/Pictures/images/2f2eb938943d.jpg");
        System.out.println(file);
    }

    /*下载单个文件*/
    public void downLoad(HttpServletRequest request, HttpServletResponse response, String lj, String fileName) throws IOException {
        OutputStream out = response.getOutputStream();
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            byte[] b = storageClient.download_file(substringFirst(lj), substringBake(lj));
            response.reset();
            response.setHeader("Content-Disposition", "attachment;filename*=utf-8'zh_cn'" + URLEncoder.encode(fileName, "UTF-8"));
            response.setHeader("Connection", "close");
            response.setHeader("Content-Type", "application/octet-stream");
            out.write(b);
        }
        catch (Exception e) {
            e.printStackTrace();
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print("<script>alert('下载失败！');window.history.go(-1);</script>");
        }
        finally {
            out.flush();
            out.close();
        }
    }

    /*下载多个文件(不是文件夹),压缩成zip*/
    /**将文件输出到指定的路径**/
    public void  downLoads(String lj, String fileName, File file, String ctxPath) throws IOException {
        try {
            String wjPath =ctxPath+ File.separator+fileName;
            File file1 =new File(wjPath);
            while (file1.isFile()){
                wjPath=wjPath.substring(0,wjPath.indexOf("."))+ System.currentTimeMillis()+wjPath.substring(wjPath.indexOf("."),wjPath.length());
                file1 =new File(wjPath);
            }
            file1.createNewFile();
            byte[] b = storageClient.download_file(substringFirst(lj), substringBake(lj));
            FileCopyUtils.copy(b, file1);

        }
        catch (Exception e) {
            e.printStackTrace();
            throw new IOException(e.getMessage());
        }
    }

    public  void downloadPhoto(){
        try {
            //String lj="/group1/M00/01/18/O2wAB12A4W-AZyKyAAAPppAeJ7c892.jpg";
            String lj="G1/M00/00/00/wKgAbV24x2aAW8n5AAAABcN2-DM532.txt";
            byte[] b = storageClient.download_file(substringFirst(lj), substringBake(lj));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    public String downloadFile(String lj){
        try {
            byte[] b = storageClient.download_file(substringFirst(lj), substringBake(lj));
            BASE64Encoder encoder = new BASE64Encoder();
            return  encoder.encode(b);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }




    public static void testFileUpload() throws Exception {
        // 1、加载配置文件，配置文件中的内容就是tracker服务的地址。
        ClientGlobal.init("C:/workspace/jgpt-bz-platform/jgpt-bz-application/src/main/resources/config.properties");
        // 2、创建一个TrackerClient对象。直接new一个。
        TrackerClient trackerClient = new TrackerClient();
        // 3、使用TrackerClient对象创建连接，获得一个TrackerServer对象。
        TrackerServer trackerServer = trackerClient.getConnection();
        // 4、创建一个StorageServer的引用，值为null
        StorageServer storageServer = null;
        // 5、创建一个StorageClient对象，需要两个参数TrackerServer对象、StorageServer的引用
        StorageClient storageClient = new StorageClient(trackerServer, storageServer);
        // 6、使用StorageClient对象上传图片。
        //扩展名不带“.”
        String[] strings = storageClient.upload_file("Y:/doc16.png", "png", null);
        // 7、返回数组。包含组名和图片的路径。
        for (String string : strings) {
            System.out.println(string);
        }
    }



    public static String substringFirst(String str){
        return  str.substring(0,str.indexOf("/"));
    }

    public static String substringBake(String str){
        return  str.substring(str.indexOf("/")+1, str.length());
    }

    //获取文件目录下所有的文件
    public static ArrayList<String> listPath(String path) {
        ArrayList<String> files = new ArrayList<String>();
        File file = new File(path);
        File[] tempList = file.listFiles();
        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                System.out.println("文件：" + tempList[i]);
                files.add(tempList[i].toString());
            }
            if (tempList[i].isDirectory()) {
                System.out.println("文件夹：" + tempList[i]);
            }
        }
        return files;
    }

    public String compressZip(String cigPath){
        //需要压缩的文件--包括文件地址和文件名
        List<String> path = FastDFSUtil.listPath(cigPath);

        // 要生成的压缩文件地址和文件名称
        String desPath = cigPath+"\\"+SysCode.ZIP_NAME;
        File zipFile = new File(desPath);
        if(!zipFile.isFile()){
            try {
                zipFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ZipOutputStream zipStream = null;
        FileInputStream zipSource = null;
        BufferedInputStream bufferStream = null;
        try {
            //构造最终压缩包的输出流
            zipStream = new ZipOutputStream(new FileOutputStream(zipFile));
            for(int i =0;i<path.size();i++){
                File file = new File(path.get(i));
                //将需要压缩的文件格式化为输入流
                zipSource = new FileInputStream(file);
                //压缩条目不是具体独立的文件，而是压缩包文件列表中的列表项，称为条目，就像索引一样
                ZipEntry zipEntry = new ZipEntry(file.getName());
                //定位该压缩条目位置，开始写入文件到压缩包中

                zipStream.putNextEntry(zipEntry);

                //输入缓冲流
                bufferStream = new BufferedInputStream(zipSource, 1024 * 10);
                int read = 0;
                //创建读写缓冲区
                byte[] buf = new byte[1024 * 10];
                while((read = bufferStream.read(buf, 0, 1024 * 10)) != -1)
                {
                    zipStream.write(buf, 0, read);
                }
            }
            zipFile.delete();
            return desPath;
        } catch (Exception e) {
            e.printStackTrace();
            return  "";
        } finally {
            //关闭流
            try {
                if(null != bufferStream) bufferStream.close();
                if(null != zipStream) zipStream.close();
                if(null != zipSource) zipSource.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void downloadFile(File file, HttpServletResponse response) {
        InputStream fin = null;
        ServletOutputStream out = null;
        try {
            fin = new FileInputStream(file);
            out = response.getOutputStream();
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/zip");
            response.addHeader("Content-Disposition", "attachment;filename="+SysCode.ZIP_NAME);

            byte[] buffer = new byte[1024];
            int bytesToRead = -1;
            // 通过循环将读入的Word文件的内容输出到浏览器中
            while((bytesToRead = fin.read(buffer)) != -1) {
                // out.write(buffer, 0, bytesToRead);
                out.write(buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(fin != null) {
                try {
                    fin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        try {
          /*  String base64=  Base64Utils.ImageToBase64ByLocal("D://JAVA图片//timg (1).jpg");
            System.out.println(base64);*/
            FastDFSUtil fastDFSUtil=new FastDFSUtil();
            String base64=  Base64Utils.ImageToBase64ByLocal("D://报价(1).xlsx");
            String result= fastDFSUtil.uploadToBase64File(base64,"xlsx");
            System.out.println(result);
            System.out.println("====================================>");
            System.out.println(fastDFSUtil.downloadFile(result));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
