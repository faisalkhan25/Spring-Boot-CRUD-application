package in.code.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GlobalExceptionResponse {
    private Integer status;
    private String message;
    private String requestURI;
}
