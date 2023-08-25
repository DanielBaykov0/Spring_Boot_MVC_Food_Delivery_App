package baykov.daniel.fooddelivery.domain.dto.view;

import baykov.daniel.fooddelivery.domain.constant.GenderEnum;
import baykov.daniel.fooddelivery.domain.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserViewDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String phoneNumber;
    private Integer age;
    private GenderEnum gender;
    private List<Role> roles;

    public List<String> getRolesNames(){
        List<String> names=new ArrayList<>();
        roles.forEach(r->names.add(r.getRole().name()));
        return names;
    }

    public boolean hasRoleWorker(){
        return getRolesNames().contains("WORKER");
    }
}
