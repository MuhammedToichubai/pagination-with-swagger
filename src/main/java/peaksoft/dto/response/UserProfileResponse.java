package peaksoft.dto.response;

import lombok.Builder;
import peaksoft.emuns.Role;

@Builder
public record UserProfileResponse(
        Long id,
        String name,
        String email,
        String avatar,
        Role role
//        List<PostByUserResponse>  postByUserResponses

) {
}
