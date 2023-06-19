package com.webtracker.app.dto;

import com.webtracker.app.model.observers.ObserverType;
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
public class DeleteUserDto {

    @NotBlank
    private String githubUsername;

    @NotNull
    private String observerType;

}
