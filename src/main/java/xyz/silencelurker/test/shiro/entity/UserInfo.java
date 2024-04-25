package xyz.silencelurker.test.shiro.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;

/**
 * @author Silence_Lurker
 */
@Data
@Entity
public class UserInfo {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private String salt;
    @JsonIgnoreProperties("userInfos")
    @ManyToMany(fetch = FetchType.EAGER)
    // @JoinTable(name = "SysUserRole", joinColumns = @JoinColumn(name = "uid"),
    // inverseJoinColumns = @JoinColumn(name = "roleId"))
    private List<SysRole> roles;
}
