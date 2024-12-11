package peaksoft.dto.request;

public record RegisterRequest(

        String name,
        String avatar,
        String email,
        String  password
) {
}
