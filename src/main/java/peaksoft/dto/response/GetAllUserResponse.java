package peaksoft.dto.response;

import lombok.Builder;
import peaksoft.emuns.Role;

import java.util.List;

@Builder
public record GetAllUserResponse(
        Long id,
        String name,
        String email,
        String avatar,
        Role role,
        List<PostByUserResponse> postByUserResponses
) {


}
