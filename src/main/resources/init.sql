-- Customer
INSERT INTO customer (first_name, last_name, email) VALUES
                                                        ('John', 'Doe', 'john.doe@example.com'),
                                                        ('Jane', 'Smith', 'jane.smith@example.com');

-- Order
INSERT INTO orders (order_date, customer_id) VALUES
                                                 ('2022-01-01', 1),
                                                 ('2022-01-02', 2);

-- OrderItem
INSERT INTO order_item (quantity, product_id, order_id) VALUES
                                                            (2, 1, 1),
                                                            (3, 2, 2);

-- Product
INSERT INTO product (product_name, price) VALUES
                                              ('Product A', 19.99),
                                              ('Product B', 29.99);
