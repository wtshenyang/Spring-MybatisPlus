package ${controllerImplPath};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ${PageUtilPath};
import ${PageVoPath};
import ${ResultDOPath};
import ${tableBeanPath}.${tableBeanName};
import ${package}.${tableName}Service;

/**
 * ${tableDesc}控制层
 * @author shenyang
 * ${dataTime}
 */
@Controller
@RequestMapping(value = "${_tableName}")
public class ${tableName}Controller  {
	@Autowired
	${tableName}Service ${_tableName}Service;
	/**
	 * 分页查询${tableDesc}数据，返回json格式
	 * @param bean
	 * @return
	 */
	@PostMapping(value = "/${_tableName}List.do")
	@ResponseBody
	public  ResultDO<?> ${_tableName}ListData(@RequestBody ${tableBeanAllName} bean) {
		return ${_tableName}Service.get${tableName}List(bean);
	}

	/**
	 * ${tableDesc}详细页面
	 * @param bean
	 * @return
	 */
	@PostMapping(value = "/${_tableName}Detail.do")
	@ResponseBody
	public ResultDO<?>  ${_tableName}infoPage(@RequestBody ${tableBeanAllName} bean) {
		return ${_tableName}Service.get${tableName}By${tableKeyName}(bean.${tableGetKey});
	}


	/**
	 * ${tableDesc}新增方法
	 * @param bean
	 * @return
	 */
	@PostMapping(value = "/${_tableName}Add.do")
	@ResponseBody
	public  ResultDO<?>  ${_tableName}AddMethod(@RequestBody ${tableBeanAllName} bean){
	    return  ${_tableName}Service.insert${tableName}(bean);
	}

	/**
	 * ${tableDesc}编辑方法
	 * @param bean
	 * @return
	 */
	@PostMapping(value = "/${_tableName}Edit.do")
	@ResponseBody
	public  ResultDO<?>  ${_tableName}EditMethod(@RequestBody ${tableBeanAllName} bean){
	    return  ${_tableName}Service.update${tableName}( bean);
	}

}