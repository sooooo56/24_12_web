package com.example.portfolio;

import com.example.portfolio.brand.Brand;
import com.example.portfolio.brand.BrandRepository;
import com.example.portfolio.item.Item;
import com.example.portfolio.item.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PortfolioApplicationTests {

	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private BrandRepository brandRepository;
	@Autowired
	private Brand brand;

	@Test
	void contextLoads() {
		Item item = Item.builder()
				.name("코듀로이 미니 스커트")
				.price(30800L)
				.discountRate(30L)
				.build();

		itemRepository.save(item);

		Brand brand = Brand.builder()
				.brandName("레더리")
				.build();

		brandRepository.save(brand);
	}

	@Test
	void test() {
		Brand brand = Brand.builder()
				.brandName("레더리")
				.build();

		brandRepository.save(brand);
	}



}
