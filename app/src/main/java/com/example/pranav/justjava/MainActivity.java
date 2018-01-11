package com.example.pranav.justjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    static int quantity = 0;
    final int pricePerUnit = 5;

    // this method is called when 'ORDER' button is pressed.
    public void submitOrder(View view)
    {
        //display(quantity);
        displayPrice(quantity * pricePerUnit);
        //String message = "Free";
        //displayMessage(message);
    }
    public void increaseQuantity(View view)
    {
        quantity++;
        display(quantity);
        //displayPrice(quantity * pricePerUnit);
    }

    public void decreaseQuantity(View view)
    {
        if (quantity > 1)
            quantity--;
        else
            quantity = 0;
        display(quantity);
       // displayPrice(quantity * pricePerUnit);
    }
    private void displayPrice(int number)
    {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        String message = "Total: " + NumberFormat.getCurrencyInstance().format(number).toString();
        message += "\nThank you!!";
        priceTextView.setText(message);
    }
    private void display(int number)
    {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    private void displayMessage(String message)
    {
        TextView    priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }

}
