package com.webtracker.app.dto.track;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrackUserDto {

    @NotBlank
    private String githubUsername;

    @NotNull
    private List<String> technologies;

    @NotBlank
    private String email;

}
