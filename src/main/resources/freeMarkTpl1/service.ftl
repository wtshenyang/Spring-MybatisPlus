package ${package};

import ${PageUtilPath};
import ${PageVoPath};
import ${ResultDOPath};
import ${tableBeanPath}.${tableBeanName};

/**
 * ${tableDesc}服务接口1
 * @author shenyang
 * ${dataTime}
 */
public interface ${tableName}Service {


	/**
	 * 分页获取 ${tableDesc}列表数据 .
	 * @param queryDO  分页参数以及检索参数
	 * @return ResultDO 分页数据
	 */
	public ResultDO<PageUtil<${tableBeanName}>> get${tableName}List(${tableBeanAllName} bean);


	/**
	 * 根据主键查询 ${tableDesc}详细
	 */
	ResultDO<${tableBeanAllName}> get${tableName}By${tableKeyName}( ${tableKeyAppendName});


	/**
	 * 新增 ${tableDesc}
	 * @param bean
	 * @return
	 */
	public ResultDO<Boolean> insert${tableName}(${tableBeanAllName} bean);


	/**
	 * 修改 ${tableDesc}信息
	 * @param bean
	 * @return
	 */
	public ResultDO<Boolean> update${tableName}(${tableBeanAllName} bean) ;


	/**
	 * 逻辑删除 ${tableDesc}
	 */
	public ResultDO<Boolean> logicDelete${tableName}(${tableKeyAppendName});
}
