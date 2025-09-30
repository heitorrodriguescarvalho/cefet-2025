package exercises.design_patterns.mvc_dao;

public class ProductController {
    private ProductDAO productDAO;
    private ProductView productView;

    public ProductController(ProductDAO productDAO, ProductView productView) {
        this.productDAO = productDAO;
        this.productView = productView;
    }

    public void run() {
        boolean running = true;

        while (running) {
            this.productView.displayMenu();
            int choice = this.productView.getMenuChoice();

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    viewProductByName();
                    break;
                case 3:
                    viewProductByIndex();
                    break;
                case 4:
                    updateProductByName();
                    break;
                case 5:
                    updateProductByIndex();
                    break;
                case 6:
                    deleteProductByName();
                    break;
                case 7:
                    deleteProductByIndex();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    this.productView.displayErrorMessage("Opção inválida. Tente novamente.");
                    break;
            }

            if (running) {
                this.productView.pressEnterToContinue();
            }
        }
    }

    private void addProduct() {
        String productName = this.productView.getProductName();

        Product product = new Product(productName);
        this.productDAO.create(product);

        this.productView.displaySuccessMessage("Produto '" + productName + "' adicionado com sucesso.");
    }

    private void viewProductByName() {
        String productName = this.productView.getProductName();
        Product product = this.productDAO.getByName(productName);

        if (product == null) {
            this.productView.displayErrorMessage("Nenhum produto encontrado com o nome '" + productName + "'.");

            return;
        }

        this.productView.displayProduct(product);
    }

    private void viewProductByIndex() {
        int index = this.productView.getProductIndex();

        Product product = this.productDAO.getByIndex(index);

        if (product == null) {
            this.productView.displayErrorMessage("Nenhum produto encontrado no índice " + index + ".");

            return;
        }

        this.productView.displayProduct(product);
    }

    private void updateProductByName() {
        String currentName = this.productView.getCurrentProductName();

        Product product = this.productDAO.getByName(currentName);

        if (product == null) {
            this.productView.displayErrorMessage("Nenhum produto encontrado com o nome '" + currentName + "'.");

            return;
        }

        String newProductName = this.productView.getNewProductName();
        boolean success = this.productDAO.updateByName(currentName, newProductName);

        if (success) {
            this.productView.displaySuccessMessage("Produto " + currentName + " atualizado com sucesso");
        } else {
            this.productView.displayErrorMessage("Falha ao atualizar o produto " + currentName);
        }
    }

    private void updateProductByIndex() {
        int index = this.productView.getProductIndex();

        Product product = this.productDAO.getByIndex(index);

        if (product == null) {
            this.productView.displayErrorMessage("Nenhum produto encontrado no índice " + index + ".");

            return;
        }

        String productName = product.getName();
        String newProductName = this.productView.getNewProductName();

        boolean success = this.productDAO.updateByIndex(index, newProductName);

        if (success) {
            this.productView.displaySuccessMessage("Produto " + productName + " atualizado com sucesso");
        } else {
            this.productView.displayErrorMessage("Falha ao atualizar o produto " + productName);
        }
    }

    private void deleteProductByName() {
        String productName = this.productView.getProductName();

        Product product = this.productDAO.getByName(productName);

        if (product == null) {
            this.productView.displayErrorMessage("Nenhum produto encontrado com o nome '" + productName + "'.");

            return;
        }

        boolean success = this.productDAO.deleteByName(productName);

        if (success) {
            this.productView.displaySuccessMessage("Produto '" + productName + "' excluído com sucesso.");
        } else {
            this.productView.displayErrorMessage("Produto '" + productName + "' não encontrado.");
        }
    }

    private void deleteProductByIndex() {
        int index = this.productView.getProductIndex();

        Product product = this.productDAO.getByIndex(index);

        if (product == null) {
            this.productView.displayErrorMessage("Nenhum produto encontrado no índice " + index + ".");

            return;
        }

        String productName = product.getName();
        boolean success = this.productDAO.deleteByIndex(index);

        if (success) {
            this.productView.displaySuccessMessage("Produto " + productName + " excluído com sucesso");
        } else {
            this.productView.displayErrorMessage("Falha ao excluir o produto " + productName);
        }
    }
}