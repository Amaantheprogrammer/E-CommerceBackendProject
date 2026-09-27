USE ecommerceDb;

INSERT INTO categories (name) VALUES
('Electronics'),
('Clothing'),
('Books'),
('Home & Kitchen'),
('Sports'),
('Beauty'),
('Toys'),
('Automotive'),
('Health'),
('Jewelry'),
('Pet Supplies'),
('Office Supplies');

INSERT INTO products
(name, price, description, stock_quantity, user_id, category_id, created_at, updated_at)
VALUES
-- Seller 9
('Wireless Mouse', 799.00, 'Ergonomic wireless mouse', 120, 9, 1, NOW(), NOW()),
('Bluetooth Keyboard', 1499.00, 'Mechanical keyboard', 80, 9, 1, NOW(), NOW()),
('Cotton T-Shirt', 499.00, 'Premium cotton tshirt', 150, 9, 2, NOW(), NOW()),
('Java Programming Book', 699.00, 'Complete Java guide', 70, 9, 3, NOW(), NOW()),
('Kitchen Knife Set', 1299.00, 'Stainless steel knives', 40, 9, 4, NOW(), NOW()),
('Football', 899.00, 'Professional football', 50, 9, 5, NOW(), NOW()),
('Face Wash', 249.00, 'Oil control face wash', 200, 9, 6, NOW(), NOW()),
('Toy Racing Car', 399.00, 'Remote control car', 60, 9, 7, NOW(), NOW()),
-- Seller 10
('Car Phone Holder', 349.00, 'Dashboard phone holder', 90, 10, 8, NOW(), NOW()),
('Vitamin C Tablets', 599.00, 'Immunity booster', 100, 10, 9, NOW(), NOW()),
('Silver Ring', 999.00, 'Sterling silver ring', 45, 10, 10, NOW(), NOW()),
('Dog Food Pack', 1299.00, 'Premium dog food', 75, 10, 11, NOW(), NOW()),
('Office Chair', 4999.00, 'Ergonomic office chair', 20, 10, 12, NOW(), NOW()),
('Laptop Stand', 899.00, 'Adjustable stand', 70, 10, 1, NOW(), NOW()),
('Formal Shirt', 1199.00, 'Slim fit shirt', 65, 10, 2, NOW(), NOW()),
('Spring Boot Guide', 799.00, 'Backend development book', 40, 10, 3, NOW(), NOW()),
-- Seller 11
('Non Stick Pan', 1499.00, 'Induction compatible pan', 35, 11, 4, NOW(), NOW()),
('Cricket Bat', 2499.00, 'English willow bat', 25, 11, 5, NOW(), NOW()),
('Lipstick Set', 699.00, 'Matte finish set', 85, 11, 6, NOW(), NOW()),
('Building Blocks', 899.00, 'Creative toy set', 55, 11, 7, NOW(), NOW()),
('Car Vacuum Cleaner', 1599.00, 'Portable vacuum', 30, 11, 8, NOW(), NOW()),
('Blood Pressure Monitor', 1999.00, 'Digital BP machine', 20, 11, 9, NOW(), NOW()),
('Gold Plated Necklace', 2999.00, 'Fashion jewelry', 15, 11, 10, NOW(), NOW()),
('Cat Food', 799.00, 'Nutrition rich cat food', 45, 11, 11, NOW(), NOW()),
-- Seller 12
('Printer Paper Pack', 499.00, 'A4 office paper', 150, 12, 12, NOW(), NOW()),
('USB-C Charger', 999.00, 'Fast charging adapter', 80, 12, 1, NOW(), NOW()),
('Denim Jeans', 1499.00, 'Slim fit jeans', 60, 12, 2, NOW(), NOW()),
('Data Structures Book', 899.00, 'DSA concepts', 30, 12, 3, NOW(), NOW()),
('Mixer Grinder', 3499.00, '750W grinder', 18, 12, 4, NOW(), NOW()),
('Badminton Racket', 1299.00, 'Professional racket', 40, 12, 5, NOW(), NOW()),
('Perfume', 1799.00, 'Long lasting fragrance', 35, 12, 6, NOW(), NOW()),
('Puzzle Game', 499.00, 'Brain teaser puzzle', 70, 12, 7, NOW(), NOW()),
-- Seller 13
('Car Air Freshener', 199.00, 'Ocean fragrance', 120, 13, 8, NOW(), NOW()),
('Protein Powder', 2499.00, 'Whey protein', 40, 13, 9, NOW(), NOW()),
('Diamond Earrings', 4999.00, 'Elegant earrings', 10, 13, 10, NOW(), NOW()),
('Bird Cage', 1599.00, 'Large bird cage', 12, 13, 11, NOW(), NOW()),
('Desk Organizer', 699.00, 'Office organizer', 55, 13, 12, NOW(), NOW()),
('Gaming Headset', 2999.00, 'RGB gaming headset', 25, 13, 1, NOW(), NOW()),
('Winter Jacket', 2499.00, 'Water resistant jacket', 30, 13, 2, NOW(), NOW()),
('Clean Code', 799.00, 'Software craftsmanship book', 35, 13, 3, NOW(), NOW()),
-- Seller 14
('Rice Cooker', 2799.00, 'Electric rice cooker', 20, 14, 4, NOW(), NOW()),
('Yoga Mat', 699.00, 'Anti slip mat', 90, 14, 5, NOW(), NOW()),
('Hair Dryer', 1199.00, 'Professional dryer', 40, 14, 6, NOW(), NOW()),
('Action Figure', 999.00, 'Collector edition', 30, 14, 7, NOW(), NOW()),
('Bike Cover', 799.00, 'Waterproof cover', 50, 14, 8, NOW(), NOW()),
('Thermometer', 299.00, 'Digital thermometer', 100, 14, 9, NOW(), NOW()),
('Bracelet', 599.00, 'Fashion bracelet', 70, 14, 10, NOW(), NOW()),
('Pet Shampoo', 399.00, 'Dog shampoo', 80, 14, 11, NOW(), NOW()),
('Whiteboard Markers', 249.00, 'Pack of 10', 200, 14, 12, NOW(), NOW()),
('Monitor Arm', 1999.00, 'Adjustable arm stand', 25, 14, 1, NOW(), NOW());