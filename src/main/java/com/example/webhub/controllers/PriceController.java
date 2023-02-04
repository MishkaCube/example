package com.example.webhub.controllers;

import com.example.webhub.price.PriceCreateDto;
import com.example.webhub.price.PriceDto;
import com.example.webhub.price.PriceUpdateDto;
import com.example.webhub.services.FileService;
import com.example.webhub.services.PriceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin("https://admin.devcloudy.ru")
@Tag(name = "Price Controller", description = "API контролера по работе с нашими услугами")

public class PriceController {

    private final PriceService priceService;
    private final FileService fileService;

    public PriceController(PriceService priceService, FileService fileService) {
        this.priceService = priceService;
        this.fileService = fileService;
    }

    @Value("${upload.path}")
    private String uploadPath;


    @Operation(description = "Получение списка услуг")
    @GetMapping(
            value = "api/getPrice",
            produces = {"application/json"}
    )
    public ResponseEntity<List<PriceDto>> getPrices() {
        return ResponseEntity.ok(priceService.getPrices());
    }

    @Operation(description = "Получение услуги")
    @GetMapping(
            value = "api/price/{priceId}",
            produces = {"application/json"}
    )
    public ResponseEntity<PriceDto> getPrice(
            @Parameter(description = "Идентификатор услуги", required = true)
            @PositiveOrZero @PathVariable("priceId") Long priceId) {
        return ResponseEntity.ok(priceService.getById(priceId));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Operation(description = "Обновление услуги")
    @PutMapping(
            value = "api/price/{priceId}",
            consumes = {"multipart/form-data"}
    )
    public ResponseEntity<PriceDto> updateProject(
            @Parameter(description = "Идентификатор услуги", required = true)
            @PositiveOrZero @PathVariable("priceId") Long priceId,
            @Parameter(description = "Запрос на обновление услуги")
            @Valid @RequestPart PriceUpdateDto request,
            @RequestParam(required = false) MultipartFile file) throws IOException {

        if (file != null && !file.isEmpty()) {
            request.setImage("https://devcloudy.ru/backimg/" + fileService.saveFile(file, uploadPath));
        } else {
            request.setImage(priceService.getById(priceId).getImage());
        }
        return ResponseEntity.ok(priceService.update(priceId, request));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Operation(description = "Удаление услуги")
    @DeleteMapping(
            value = "api/price/{priceId}"
    )
    public ResponseEntity<Void> deletePrice(
            @Parameter(description = "Идентификатор услуги для удаления", required = true)
            @PositiveOrZero @PathVariable("priceId") Long priceId) {
        priceService.deleteById(priceId);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Operation(description = "Создание услуги")
    @PostMapping(
            value = "api/price",
            consumes = {"multipart/form-data"}
    )
    public ResponseEntity<PriceDto> createProject(
            @Parameter(description = "Запрос на создание услуги")
            @RequestPart PriceCreateDto request,
            @RequestParam(value = "file") MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            request.setImage("https://devcloudy.ru/backimg/" + fileService.saveFile(file, uploadPath));
        }
        return new ResponseEntity<>(priceService.create(request), HttpStatus.CREATED);
    }


}


