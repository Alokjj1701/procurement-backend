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