package com.ecommerce.infra.controllers;

import com.ecommerce.aplication.records.FavoriteProductRecords.DataFavoriteProductRequest;
import com.ecommerce.aplication.records.FavoriteProductRecords.DataFavoriteProductResponse;
import com.ecommerce.aplication.services.ServiceFavoriteProducts;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favoritos")
public class FavoriteController {
    private static final Logger logger = LoggerFactory.getLogger(FavoriteController.class);
    private final ServiceFavoriteProducts service;

    public FavoriteController(ServiceFavoriteProducts service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> add(@AuthenticationPrincipal(expression = "id") Long userId,
                                    @Valid @RequestBody DataFavoriteProductRequest request) {
        logger.info("Usuario {} Adicionando Produto {} a Favoritos", userId, request.productId());
        service.add(userId, request);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/recomendacoes")
    public ResponseEntity<List<DataFavoriteProductResponse>> recommendation(@AuthenticationPrincipal(expression = "id") Long userId) {
        logger.info("Usuario {} solicitando recomendações baseadas nos favoritos", userId);
        List<DataFavoriteProductResponse> recomendados = service.recommend(userId);
        return ResponseEntity.ok(recomendados);
    }

    @DeleteMapping("/{produtoId}")
    public ResponseEntity<Void> remove(@AuthenticationPrincipal(expression = "id") Long userId,
                                       @PathVariable Long produtoId) {
        logger.info("Usuario {} Removendo Produto {} De Favoritos", userId, produtoId);
        service.remove(userId, produtoId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<DataFavoriteProductResponse>> list(@AuthenticationPrincipal(expression = "id") Long userId) {
        logger.info("Usuario {} Buscnado por Produtos Favoritos", userId);
        List<DataFavoriteProductResponse> favorites = service.list(userId);
        return ResponseEntity.ok(favorites);
    }
}
