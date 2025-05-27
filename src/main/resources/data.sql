TRUNCATE TABLE request, product, supplier, users RESTART IDENTITY CASCADE;
-- Insert admin user (password: admin123)
INSERT INTO users (email, password, name, department, role, created_at, updated_at)
VALUES ('admin@company.com', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'Admin User', 'IT', 'ADMIN', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert requester user (password: requester123)
INSERT INTO users (email, password, name, department, role, created_at, updated_at)
VALUES ('requester@company.com', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'Requester User', 'HR', 'REQUESTER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert reviewer user (password: reviewer123)
INSERT INTO users (email, password, name, department, role, created_at, updated_at)
VALUES ('reviewer@company.com', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'Reviewer User', 'Finance', 'REVIEWER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert sourcing manager user (password: manager123)
INSERT INTO users (email, password, name, department, role, created_at, updated_at)
VALUES ('manager@company.com', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'Sourcing Manager', 'Procurement', 'SOURCING_MANAGER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert test suppliers
INSERT INTO supplier (name, email, phone, category, contact_person, status)
VALUES 
('Tech Supplies Inc', 'contact@techsupplies.com', '123-456-7890', 'Technology', 'John Doe', 'Active'),
('Office Essentials', 'info@officeessentials.com', '987-654-3210', 'Office Supplies', 'Jane Smith', 'Active');

-- Insert test products
INSERT INTO product (name, description, category, price, supplier_id)
VALUES 
('Laptop', 'High-performance laptop', 'Electronics', 999.99, 1),
('Office Chair', 'Ergonomic office chair', 'Furniture', 199.99, 2);

-- Insert test requests
INSERT INTO request (requested_by, quantity, status, justification, product_id)
VALUES 
('user1@example.com', 2, 'Pending', 'Need for new employees', 1),
('user2@example.com', 1, 'In Progress', 'Replacement for damaged equipment', 2),
('user3@example.com', 3, 'Pending', 'Office supplies for new department', 1); 