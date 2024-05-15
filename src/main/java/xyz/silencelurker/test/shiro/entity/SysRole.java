package xyz.silencelurker.test.shiro.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * @author Silence_Lurker
 */
@Data
@Entity
public class SysRole {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "roles" })
    private List<SysPermission> permissions;

    // @ManyToMany(fetch = FetchType.LAZY)
    // @JsonIgnoreProperties(value = { "roles" })
    // private List<UserInfo> userInfos;
}
