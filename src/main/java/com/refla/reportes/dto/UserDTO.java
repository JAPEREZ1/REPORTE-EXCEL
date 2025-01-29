package com.refla.reportes.dto;
import lombok.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    Long id;
    String username;
    String password;
    String roles;
    String permissions;
    Integer blocked;
    Integer active;


    String createdBy;
    Date createdDate;
    String updatedBy;
    Date updatedDate;

}