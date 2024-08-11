package com.example.demomion;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductListController {
    @FXML
    TableView<Product> productTable;
    @FXML
    private TableColumn<Product, Integer> idColumn;
    @FXML
    private TableColumn<Product, Image> imageColumn;
    @FXML
    private TableColumn<Product, String> nameColumn;
    @FXML
    private TableColumn<Product, Double> priceColumn;
    @FXML
    private TableColumn<Product, Integer> quantityColumn;
    @FXML
    private TableColumn<Product, Void> actionColumn;

    private Runnable addProductHandler;

    private ObservableList<Product> products = FXCollections.observableArrayList();
    private Stage stage;

    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(productTable.getItems().indexOf(cellData.getValue()) + 1).asObject());

        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        quantityColumn.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());

        imageColumn.setCellValueFactory(cellData -> cellData.getValue().imageProperty());
        imageColumn.setCellFactory(col -> new TableCell<Product, Image>() {
            private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(Image image, boolean empty) {
                super.updateItem(image, empty);
                if (empty || image == null) {
                    setGraphic(null);
                } else {
                    imageView.setImage(image);
                    imageView.setFitWidth(50);
                    imageView.setFitHeight(50);
                    setGraphic(imageView);
                }
            }
        });
        actionColumn.setCellFactory(col -> new TableCell<Product, Void>() {
            private final Button updateButton = new Button("Update");
            private final Button deleteButton = new Button("Delete");
            private final Button buyButton = new Button("Buy");

            {
                updateButton.setOnAction(e -> {
                    Product product = getTableView().getItems().get(getIndex());
                    handleEdit(product);
                });

                deleteButton.setOnAction(e -> {
                    Product product = getTableView().getItems().get(getIndex());
                    if (showConfirmation("Bạn muốn xóa sản phẩm này không?")) {
                        handleDelete(product);
                    }
                });
                buyButton.setOnAction(e -> {
                    Product product = getTableView().getItems().get(getIndex());
                    if(showConfirmation("Bạn muốn thêm sản phẩm vào giỏ hàng không?")){
                        handleBuy(product);
                    }
                });

                HBox buttons = new HBox(10, updateButton, deleteButton, buyButton);
                buttons.setPadding(new Insets(5));
                setGraphic(buttons);
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : getGraphic());
            }
        });
        productTable.setItems(products);

        List<Product> loadedProducts = loadProductsFromFile();
        products.addAll(loadedProducts);

        List<Product> buyProducts = buyProductsFromFile();
        products.addAll(buyProducts);
    }

//    Mua sản phẩm
    @FXML
    private void handleBuy(Product product) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Buy.fxml"));
            BorderPane root = loader.load();

            AddProductController controller = loader.getController();
            controller.setStage(stage);
            controller.setProducts(products);

            Stage buyProductStage = new Stage();
            buyProductStage.setScene(new Scene(root));
            buyProductStage.setTitle("Buy Product");
            buyProductStage.initOwner(stage);
            buyProductStage.initModality(Modality.WINDOW_MODAL);

            buyProductStage.setOnHiding(e -> {
                productTable.refresh();
                buyProductsToFile();

            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
// Thêm sản phẩm
    @FXML
    private void handleAddProduct() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddProduct.fxml"));
            BorderPane root = loader.load();

            AddProductController controller = loader.getController();
            controller.setStage(stage);
            controller.setProducts(products);

            Stage addProductStage = new Stage();
            addProductStage.setScene(new Scene(root));
            addProductStage.setTitle("Add Product");
            addProductStage.initOwner(stage);
            addProductStage.initModality(Modality.WINDOW_MODAL);

            addProductStage.setOnHiding(e -> {
                productTable.refresh();
                saveProductsToFile();

            });

            addProductStage.show();
        } catch (IOException e) {
            showError("Error loading add product window: " + e.getMessage());
        }
    }
// Cập nhật sản phẩm
    private void handleEdit(Product product) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Update_Product.fxml"));
            BorderPane root = loader.load();

            AddProductController controller = loader.getController();
            controller.setStage(stage);
            controller.setProducts(products);
            controller.setProductListController(this);
            controller.setCurrentProduct(product);

            Stage editProductStage = new Stage();
            editProductStage.setScene(new Scene(root));
            editProductStage.setTitle("Edit Product");
            editProductStage.initOwner(stage);
            editProductStage.initModality(Modality.WINDOW_MODAL);

            editProductStage.setOnHiding(e -> {
                refreshTable();
                saveProductsToFile();
            });

            editProductStage.show();
        } catch (IOException e) {
            showError("Error loading edit product window: " + e.getMessage());
        }
    }
// Xóa sản phẩm
    private void handleDelete(Product product) {
        products.remove(product);
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

    private boolean showConfirmation(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText(message);
        return alert.showAndWait().get() == ButtonType.OK;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private List<Product> loadProductsFromFile() {
        List<Product> products = new ArrayList<>();
        File file = new File("project.txt");
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Product product = Product.fromString(line);
                    products.add(product);
                }
            } catch (IOException e) {
                showError("Failed to load products: " + e.getMessage());
            }
        }
        return products;
    }

    private List<Product> buyProductsFromFile() {
        List<Product> products = new ArrayList<>();
        File file = new File("cart.txt");
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Product product = Product.fromString(line);
                    products.add(product);
                }
            } catch (IOException e) {
                showError("Failed to load products: " + e.getMessage());
            }
        }
        return products;
    }

    private void saveProductsToFile() {
        try (FileWriter writer = new FileWriter("project.txt", false)) {
            for (Product product : products) {
                writer.write(product.toString() + System.lineSeparator());
            }
        } catch (IOException e) {
            showError("Failed to save products: " + e.getMessage());
        }
    }
    private void buyProductsToFile() {
        try (FileWriter writer = new FileWriter("cart.txt", false)) {
            for (Product product : products) {
                writer.write(product.toString() + System.lineSeparator());
            }
        } catch (IOException e) {
            showError("Failed to save products: " + e.getMessage());
        }
    }

    public void refreshTable() {
        productTable.refresh();
    }
}
