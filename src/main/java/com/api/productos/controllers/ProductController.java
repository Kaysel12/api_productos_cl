package com.api.productos.controllers;

import com.api.productos.models.ProductModel;
import com.api.productos.services.ProductService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductController {

    @Inject
    private ProductService productService;

    @GET
    public Response getAllProducts() {
        List<ProductModel> products = productService.getAllProducts();
        return Response.ok(products).build();
    }

    @POST
    public Response createProduct(ProductModel product) {
        ProductModel createdProduct = productService.createProduct(product);
        return Response.status(Response.Status.CREATED).entity(createdProduct).build();
    }

    @GET
    @Path("/{id}")
    public Response getProductById(@PathParam("id") Long id) {
        ProductModel product = productService.getProductById(id);
        if (product != null) {
            return Response.ok(product).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateProduct(@PathParam("id") Long id, ProductModel product) {
        ProductModel updatedProduct = productService.updateProduct(id, product);
        if (updatedProduct != null) {
            return Response.ok(updatedProduct).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteProduct(@PathParam("id") Long id) {
        boolean isDeleted = productService.deleteProduct(id);
        if (isDeleted) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
