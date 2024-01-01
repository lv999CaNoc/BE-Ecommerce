package com.example.beecommerce.service.implement;

import com.example.beecommerce.exception.UserNotFoundException;
import com.example.beecommerce.pojo.entity.Color;
import com.example.beecommerce.pojo.entity.Image;
import com.example.beecommerce.pojo.entity.Product;
import com.example.beecommerce.pojo.entity.Size;
import com.example.beecommerce.pojo.requests.ColorRequest;
import com.example.beecommerce.pojo.requests.ImageRequest;
import com.example.beecommerce.pojo.requests.ImageUpdateRequest;
import com.example.beecommerce.pojo.requests.SizeRequest;
import com.example.beecommerce.repository.ColorRepository;
import com.example.beecommerce.repository.ImageRepository;
import com.example.beecommerce.repository.ProductRepository;
import com.example.beecommerce.repository.SizeRepository;
import com.example.beecommerce.service.ColorService;
import com.example.beecommerce.service.ImageService;
import com.example.beecommerce.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDetailImplementService implements ColorService, ImageService, SizeService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SizeRepository sizeRepository;
    @Autowired
    private ColorRepository colorRepository;
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public Size createSize(SizeRequest sizeRequest) {
        Size size = new Size();

        Product product = productRepository.findProductById(sizeRequest.getId_product());
        if (product == null) {
            throw new RuntimeException("Cannot find product by id: " + sizeRequest.getId_product());
        }
        size.setProduct(product);
        size.setSizes(sizeRequest.getSizes());

        return sizeRepository.save(size);
    }

    @Override
    public void deleteSize(Long id) {
        Size size = sizeRepository.findSizeById(id);
        if (size == null) {
            throw new RuntimeException("Cannot find size by id: " + id);
        }
        sizeRepository.delete(size);
    }

    @Override
    public List<Size> listSizeAll() {
        List<Size> sizes = sizeRepository.findAll();
        return sizes;
    }

    @Override
    public List<Size> listSizeByProductID(Long id) {
        List<Size> sizes = sizeRepository.findSizesByProductId(id);
        return sizes;
    }


    @Override
    public Color createColor(ColorRequest colorRequest) {
        Color color = new Color();

        Product product = productRepository.findProductById(colorRequest.getId_product());
        if (product == null) {
            throw new RuntimeException("Cannot find product by id: " + colorRequest.getId_product());
        }
        color.setProduct(product);
        color.setColors(colorRequest.getColors());

        return colorRepository.save(color);
    }

    @Override
    public List<Color> listColorAll() {
        List<Color> colors = colorRepository.findAll();
        return colors;
    }

    @Override
    public void deleteColor(Long id) {
        Color color = colorRepository.findColorById(id);
        if (color == null) {
            throw new RuntimeException("Cannot find color by id: " + id);
        }
        colorRepository.delete(color);
    }

    @Override
    public List<Color> listColorByProductID(Long id) {
        List<Color> colors = colorRepository.findColorByProductId(id);
        return colors;
    }

    @Override
    public Image createImage(ImageRequest imageRequest) {
        Image image = new Image();
        Product product = productRepository.findProductById(imageRequest.getId_product());
        if (product == null) {
            throw new UserNotFoundException("Cannot find product by id: " + imageRequest.getId_product());
        }
        image.setProduct(product);
        image.setTitle(imageRequest.getTitle());
        image.setUrls(imageRequest.getUrls());

        return imageRepository.save(image);
    }

    @Override
    public List<Image> listImageAll() {
        List<Image> images = imageRepository.findAll();
        return images;
    }

    @Override
    public void deleteImage(Long id) {
        Image image = imageRepository.findImageById(id);
        if (image == null) {
            throw new UserNotFoundException("Cannot find image by id: " + id);
        }
        imageRepository.delete(image);
    }

    @Override
    public Image updateImage(ImageUpdateRequest imageUpdateRequest, Long id) {
        Image image = imageRepository.findImageById(id);
        if (image == null) {
            throw new UserNotFoundException("Cannot find image by id: " + id);
        }
        image.setTitle(imageUpdateRequest.getTitle());
        image.setUrls(imageUpdateRequest.getUrls());

        return imageRepository.save(image);
    }

    @Override
    public List<Image> listImageByProductID(Long id) {
        List<Image> images = imageRepository.findImageByProductId(id);
        return images;
    }


}
