package peaksoft.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import peaksoft.emuns.Role;

@Data
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private Role role;
}
