-- =========================================
-- CATEGORIES
-- =========================================
INSERT INTO categories (name) VALUES
('Electronics'),
('Mobile Phones'),
('Laptops'),
('Clothing'),
('Footwear'),
('Home & Kitchen'),
('Toys'),
('Books'),
('Sports & Fitness'),
('Beauty & Personal Care');

INSERT INTO categories (name) VALUES
('Furniture'),
('Stationery'),
('Musical Instruments'),
('Pet Supplies'),
('Automotive'),
('Garden & Outdoor'),
('Watches'),
('Bags & Luggage'),
('Baby Care'),
('Health & Wellness');

-- =========================================
-- USERS
-- (password hashes are placeholders, not usable for real login)
-- =========================================
INSERT INTO users (name, email, password, role) VALUES
('Amaan Sheikh', 'amaan@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMy.MrZGgQXg6V0t6F7G8z8q1aR9bX3eW8K', 'ROLE_ADMIN'),
('Priya Sharma', 'priya.sharma@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMy.MrZGgQXg6V0t6F7G8z8q1aR9bX3eW8K', 'ROLE_USER'),
('Rahul Verma', 'rahul.verma@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMy.MrZGgQXg6V0t6F7G8z8q1aR9bX3eW8K', 'ROLE_USER'),
('Sneha Iyer', 'sneha.iyer@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMy.MrZGgQXg6V0t6F7G8z8q1aR9bX3eW8K', 'ROLE_USER'),
('Karan Mehta', 'karan.mehta@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMy.MrZGgQXg6V0t6F7G8z8q1aR9bX3eW8K', 'ROLE_USER'),
('Ananya Reddy', 'ananya.reddy@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMy.MrZGgQXg6V0t6F7G8z8q1aR9bX3eW8K', 'ROLE_USER'),
('Vikram Singh', 'vikram.singh@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMy.MrZGgQXg6V0t6F7G8z8q1aR9bX3eW8K', 'ROLE_USER'),
('Neha Gupta', 'neha.gupta@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMy.MrZGgQXg6V0t6F7G8z8q1aR9bX3eW8K', 'ROLE_USER'),
('Arjun Nair', 'arjun.nair@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMy.MrZGgQXg6V0t6F7G8z8q1aR9bX3eW8K', 'ROLE_USER'),
('Divya Pillai', 'divya.pillai@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMy.MrZGgQXg6V0t6F7G8z8q1aR9bX3eW8K', 'ROLE_USER');

-- =========================================
-- PRODUCTS
-- category_id references match insertion order above:
-- 1 Electronics, 2 Mobile Phones, 3 Laptops, 4 Clothing,
-- 5 Footwear, 6 Home & Kitchen, 7 Toys, 8 Books,
-- 9 Sports & Fitness, 10 Beauty & Personal Care
-- =========================================
INSERT INTO products (name, description, price, stock_quantity, category_id, created_at, updated_at) VALUES
('iPhone 15', 'Apple iPhone 15 with 128GB storage, A16 Bionic chip, dual camera system.', 79900.00, 25, 2, NOW(), NOW()),
('Samsung Galaxy S24', 'Samsung flagship with 256GB storage, Snapdragon 8 Gen 3, AMOLED display.', 74999.00, 18, 2, NOW(), NOW()),
('OnePlus 12', '12GB RAM, 256GB storage, Hasselblad camera, 100W fast charging.', 64999.00, 30, 2, NOW(), NOW()),
('Dell XPS 13', '13-inch laptop, Intel i7, 16GB RAM, 512GB SSD, InfinityEdge display.', 119999.00, 12, 3, NOW(), NOW()),
('MacBook Air M2', 'Apple MacBook Air with M2 chip, 8GB RAM, 256GB SSD, 13.6-inch display.', 114900.00, 15, 3, NOW(), NOW()),
('HP Pavilion 15', '11th Gen Intel i5, 8GB RAM, 512GB SSD, 15.6-inch FHD display.', 54999.00, 20, 3, NOW(), NOW()),
('Sony WH-1000XM5', 'Industry-leading noise cancelling wireless headphones.', 29990.00, 40, 1, NOW(), NOW()),
('JBL Flip 6', 'Portable waterproof Bluetooth speaker with deep bass.', 9999.00, 60, 1, NOW(), NOW()),
('Men''s Cotton T-Shirt', 'Regular fit, 100% cotton, available in multiple colors.', 599.00, 150, 4, NOW(), NOW()),
('Women''s Denim Jacket', 'Classic blue denim jacket with button closure.', 1799.00, 75, 4, NOW(), NOW()),
('Formal Shirt - Slim Fit', 'Wrinkle-free formal shirt, ideal for office wear.', 1299.00, 90, 4, NOW(), NOW()),
('Nike Air Max 270', 'Men''s running shoes with large Air unit for comfort.', 12995.00, 35, 5, NOW(), NOW()),
('Adidas Ultraboost 22', 'Responsive running shoes with Boost midsole.', 15999.00, 28, 5, NOW(), NOW()),
('Bata Formal Leather Shoes', 'Genuine leather formal shoes, slip-resistant sole.', 2499.00, 50, 5, NOW(), NOW()),
('Non-Stick Cookware Set', '5-piece non-stick cookware set with induction base.', 3499.00, 40, 6, NOW(), NOW()),
('Electric Kettle 1.8L', 'Stainless steel electric kettle with auto shut-off.', 1199.00, 80, 6, NOW(), NOW()),
('Memory Foam Pillow', 'Orthopedic cervical pillow for neck support.', 899.00, 100, 6, NOW(), NOW()),
('Lego Classic Bricks Set', '500-piece creative building blocks set for kids.', 2999.00, 45, 7, NOW(), NOW()),
('Remote Control Car', 'High-speed RC car with rechargeable battery.', 1599.00, 60, 7, NOW(), NOW()),
('Atomic Habits', 'Bestselling book by James Clear on building good habits.', 499.00, 200, 8, NOW(), NOW()),
('The Alchemist', 'Paulo Coelho''s classic novel of self-discovery.', 299.00, 180, 8, NOW(), NOW()),
('Yoga Mat 6mm', 'Anti-slip eco-friendly yoga mat with carry strap.', 899.00, 70, 9, NOW(), NOW()),
('Adjustable Dumbbell Set', '2-20kg adjustable dumbbells for home workouts.', 4999.00, 25, 9, NOW(), NOW()),
('Cricket Bat - English Willow', 'Grade 1 English willow bat, lightweight design.', 5999.00, 20, 9, NOW(), NOW()),
('Vitamin C Face Serum', 'Brightening face serum with 10% Vitamin C.', 649.00, 120, 10, NOW(), NOW()),
('Herbal Shampoo 400ml', 'Sulfate-free herbal shampoo for all hair types.', 399.00, 150, 10, NOW(), NOW());

INSERT INTO products (name, description, price, stock_quantity, category_id, created_at, updated_at) VALUES
('Apple Watch Series 9', 'GPS, 45mm case, always-on Retina display, heart rate sensor.', 41900.00, 22, 1, NOW(), NOW()),
('Logitech MX Master 3S', 'Wireless ergonomic mouse with ultra-fast scrolling.', 8999.00, 50, 1, NOW(), NOW()),
('Canon EOS R10', 'Mirrorless camera with 24.2MP sensor and 4K video.', 84999.00, 10, 1, NOW(), NOW()),
('Anker Power Bank 20000mAh', 'Fast-charging power bank with dual USB output.', 2499.00, 100, 1, NOW(), NOW()),
('Google Pixel 8', '128GB storage, Tensor G3 chip, AI-powered camera.', 69999.00, 15, 2, NOW(), NOW()),
('Xiaomi Redmi Note 13', '6GB RAM, 128GB storage, 108MP camera, budget flagship.', 18999.00, 60, 2, NOW(), NOW()),
('Lenovo ThinkPad E14', 'Business laptop, Intel i5, 16GB RAM, 512GB SSD.', 64999.00, 18, 3, NOW(), NOW()),
('ASUS ROG Strix G15', 'Gaming laptop, Ryzen 7, RTX 4060, 16GB RAM.', 99999.00, 8, 3, NOW(), NOW()),
('Women''s Kurti Set', 'Printed cotton kurti with matching palazzo pants.', 1499.00, 85, 4, NOW(), NOW()),
('Men''s Joggers', 'Slim fit joggers with elastic waistband, multiple colors.', 899.00, 110, 4, NOW(), NOW()),
('Kids Hooded Sweatshirt', 'Warm fleece hoodie for children, ages 4-10.', 799.00, 70, 4, NOW(), NOW()),
('Puma Running Shoes', 'Lightweight breathable mesh running shoes.', 3499.00, 45, 5, NOW(), NOW()),
('Crocs Classic Clog', 'Comfortable slip-on clogs, water-friendly.', 2999.00, 65, 5, NOW(), NOW()),
('Air Fryer 4.5L', 'Digital air fryer with 8 preset cooking modes.', 4999.00, 30, 6, NOW(), NOW()),
('Ceramic Dinner Set 24pc', 'Microwave-safe ceramic dinnerware set for 6.', 2799.00, 25, 6, NOW(), NOW()),
('Bedsheet Set King Size', '100% cotton bedsheet with 2 pillow covers.', 1299.00, 55, 6, NOW(), NOW()),
('Wooden Building Blocks', 'Eco-friendly wooden blocks for toddlers, 50 pieces.', 1299.00, 40, 7, NOW(), NOW()),
('Barbie Dreamhouse Mini', 'Compact dollhouse playset with furniture.', 3499.00, 20, 7, NOW(), NOW()),
('Rich Dad Poor Dad', 'Personal finance classic by Robert Kiyosaki.', 399.00, 150, 8, NOW(), NOW()),
('Sapiens: A Brief History of Humankind', 'Yuval Noah Harari''s exploration of human history.', 599.00, 95, 8, NOW(), NOW()),
('Resistance Bands Set', '5-piece resistance bands with door anchor.', 699.00, 90, 9, NOW(), NOW()),
('Badminton Racket Pro', 'Carbon fiber racket with cover, professional grade.', 1999.00, 35, 9, NOW(), NOW()),
('Sunscreen SPF 50', 'Broad spectrum, non-greasy, water-resistant sunscreen.', 449.00, 200, 10, NOW(), NOW()),
('Electric Trimmer', 'Cordless rechargeable trimmer with 4 attachments.', 1299.00, 75, 10, NOW(), NOW()),
('Study Table with Drawer', 'Engineered wood study desk, compact design.', 3999.00, 18, 11, NOW(), NOW()),
('Ergonomic Office Chair', 'Mesh back office chair with lumbar support.', 6499.00, 22, 11, NOW(), NOW()),
('Wooden Bookshelf 5-Tier', 'Open bookshelf, sturdy MDF construction.', 2999.00, 15, 11, NOW(), NOW()),
('Notebook Set of 5', 'Ruled notebooks, 200 pages each, spiral bound.', 299.00, 200, 12, NOW(), NOW()),
('Gel Pen Pack of 10', 'Smooth-writing gel pens, assorted colors.', 199.00, 250, 12, NOW(), NOW()),
('Acoustic Guitar 40-inch', 'Beginner-friendly acoustic guitar with bag.', 4999.00, 12, 13, NOW(), NOW()),
('Digital Keyboard 61-Key', 'Portable keyboard with built-in speakers.', 7999.00, 10, 13, NOW(), NOW()),
('Dog Food 3kg', 'Balanced nutrition dry dog food for adult dogs.', 899.00, 60, 14, NOW(), NOW()),
('Cat Scratching Post', 'Sisal rope scratching post with hanging toy.', 1499.00, 30, 14, NOW(), NOW()),
('Car Vacuum Cleaner', 'Portable 12V vacuum cleaner for car interiors.', 1299.00, 40, 15, NOW(), NOW()),
('Microfiber Car Wash Towel Set', 'Pack of 5 lint-free microfiber towels.', 599.00, 80, 15, NOW(), NOW()),
('Garden Tool Set 5pc', 'Hand trowel, fork, pruner, rake, and gloves.', 899.00, 45, 16, NOW(), NOW()),
('Outdoor Solar Lights Pack of 6', 'Waterproof LED solar garden lights.', 1199.00, 55, 16, NOW(), NOW()),
('Fastrack Analog Watch', 'Stainless steel strap, water-resistant.', 1999.00, 50, 17, NOW(), NOW()),
('Fossil Chronograph Watch', 'Leather strap, chronograph dial, gift box.', 8999.00, 20, 17, NOW(), NOW()),
('Laptop Backpack 15.6-inch', 'Water-resistant backpack with USB charging port.', 1799.00, 65, 18, NOW(), NOW()),
('Trolley Suitcase 24-inch', 'Hardshell expandable suitcase with TSA lock.', 4499.00, 25, 18, NOW(), NOW()),
('Baby Diapers Pack of 50', 'Ultra-soft diapers with wetness indicator.', 899.00, 100, 19, NOW(), NOW()),
('Baby Stroller Foldable', 'Lightweight stroller with adjustable canopy.', 5999.00, 15, 19, NOW(), NOW()),
('Digital BP Monitor', 'Automatic blood pressure monitor with memory.', 1899.00, 35, 20, NOW(), NOW()),
('Multivitamin Tablets 60ct', 'Daily multivitamin for immunity and energy.', 549.00, 120, 20, NOW(), NOW());

-- Set Users 1, 2, 4, 5, 7, and 8 to Cash on Delivery
UPDATE users SET payment_method = 'CASH_ON_DELIVERY' WHERE id IN (1, 2, 4, 5, 7, 8);

-- Set Users 3, 6, 9, and 10 to Bank Transfer
UPDATE users SET payment_method = 'BANK_TRANSFER' WHERE id IN (3, 6, 9, 10);

INSERT INTO bank_accounts (balance, user_id) VALUES
(12500.50, 3),   -- Rahul Verma (Healthy balance)
(3200.00, 6),    -- Ananya Reddy (Low balance to test transaction failures)
(8500.75, 9),    -- Arjun Nair (Standard balance)
(25000.00, 10);  -- Divya Pillai (Premium balance)