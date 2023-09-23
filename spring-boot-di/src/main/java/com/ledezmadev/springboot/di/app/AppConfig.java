package com.ledezmadev.springboot.di.app;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.ledezmadev.springboot.di.app.domain.ItemFactura;
import com.ledezmadev.springboot.di.app.domain.Producto;
import com.ledezmadev.springboot.di.app.service.IServicio;
import com.ledezmadev.springboot.di.app.service.MiServicio;
import com.ledezmadev.springboot.di.app.service.MiServicioComplejo;

@Configuration
public class AppConfig {

	@Bean("miServicioSimple")
	@Primary
	public IServicio registrarMiServicio() {
		return new MiServicio();
	}
	
	@Bean("miServicioSimpleComplejo")
	public IServicio registrarMiServicioComplejo() {
		return new MiServicioComplejo();
	}
	
	@Bean("itemsFactura")
	public List<ItemFactura> registrarItems(){
		
		Producto producto1 = new Producto("Matias bebé ", 100);
		Producto producto2 = new Producto("Matias adolecente ", 200);
		Producto producto3 = new Producto("Matias aulto ", 300);
		
		ItemFactura linea1 = new ItemFactura(producto1, 1);
		ItemFactura linea2 = new ItemFactura(producto2, 2);
		ItemFactura linea3 = new ItemFactura(producto3, 3);
		
		return Arrays.asList(linea1, linea2, linea3);
	}
	
	@Bean("itemsFacturaOficina")
	@Primary
	public List<ItemFactura> registrarItemsOficina(){
		
		Producto producto1 = new Producto("Monitor ", 100);
		Producto producto2 = new Producto("CPU ", 200);
		Producto producto3 = new Producto("Mouse", 300);
		
		ItemFactura linea1 = new ItemFactura(producto1, 1);
		ItemFactura linea2 = new ItemFactura(producto2, 2);
		ItemFactura linea3 = new ItemFactura(producto3, 3);
		
		return Arrays.asList(linea1, linea2, linea3);
	}
	
}
