from PIL import Image

# Đọc hình ảnh từ đường dẫn
image = Image.open("home.png")

# Thay đổi kích thước hình ảnh thành 30x30 pixels
resized_image = image.resize((20, 20))

# Lưu hình ảnh đã thay đổi kích thước
resized_image.save("home.png")
