
package me.zhengjie.modules.mnt.service.dto;

import lombok.Data;
import me.zhengjie.annotation.Query;
import java.sql.Timestamp;
import java.util.List;

@Data
public class ServerDeployQueryCriteria{

	/**
	 * 模糊
	 */
	@Query(blurry = "name,ip,account")
    private String blurry;

	@Query(type = Query.Type.BETWEEN)
	private List<Timestamp> createTime;
}
