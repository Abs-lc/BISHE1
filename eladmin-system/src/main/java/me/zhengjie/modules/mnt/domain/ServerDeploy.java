
package me.zhengjie.modules.mnt.domain;

import io.swagger.annotations.ApiModelProperty;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.Getter;
import lombok.Setter;
import me.zhengjie.base.BaseEntity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Entity
@Getter
@Setter
@Table(name="mnt_server")
public class ServerDeploy extends BaseEntity implements Serializable {

    @Id
    @Column(name = "server_id")
    @ApiModelProperty(value = "ID", hidden = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(value = "服务名称")
    private String name;

    @ApiModelProperty(value = "服务ID")
    private String ip;

    @ApiModelProperty(value = "所属系统")
    private Integer port;

    @ApiModelProperty(value = "报文格式")
    private String account;

    @ApiModelProperty(value = "服务调用地址")
    private String password;

    @ApiModelProperty(value = "服务版本")
    private String version;

    @ApiModelProperty(value = "服务状态")
    private String state;

    @ApiModelProperty(value = "服务描述")
    private String description;

    public void copy(ServerDeploy source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ServerDeploy that = (ServerDeploy) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
