package org.example.ibmskillsbuildapp.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserRoles {
    @Id
    private String RoleName;
    public UserRoles() {
    }

    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String RoleName) {
        this.RoleName = RoleName;
    }
}
