// Invoice.java
import java.util.ArrayList;
import java.util.List;

public class Invoice {
    private String title;
    private String customerAddress;
    private List<LineItem> lineItems;
    private double totalAmountDue;

    public Invoice(String title, String customerAddress) {
        this.title = title;
        this.customerAddress = customerAddress;
        this.lineItems = new ArrayList<>();
        this.totalAmountDue = 0.0;
    }

    public void addLineItem(LineItem lineItem) {
        lineItems.add(lineItem);
        calculateTotalAmountDue();
    }

    public void removeLineItem(LineItem lineItem) {
        lineItems.remove(lineItem);
        calculateTotalAmountDue();
    }

    private void calculateTotalAmountDue() {
        totalAmountDue = 0.0;
        for (LineItem lineItem : lineItems) {
            totalAmountDue += lineItem.getTotalCost();
        }
    }

    public double getTotalAmountDue() {
        return totalAmountDue;
    }

    public String getTitle() {
        return title;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }
}
