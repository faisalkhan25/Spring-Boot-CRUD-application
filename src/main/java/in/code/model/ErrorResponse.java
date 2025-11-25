package in.code.model;

import lombok.*;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ErrorResponse {
    private Integer status;
    private String message;
    private Instant timestamp;
    private String requestUri;

}
