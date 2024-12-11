package peaksoft.dto.response;

import jakarta.persistence.ElementCollection;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record PostByUserResponse(
        Long id,
        String description,
        List<String> images,
        LocalDate cratedData,
        LocalDate updatedData

) {
}
