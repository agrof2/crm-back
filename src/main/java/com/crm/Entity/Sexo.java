package com.crm.Entity;

import java.util.Arrays;

public enum Sexo {
	MASC(1, "Masculino") {
		@Override
		public String toString() {
			return getName();
		}
	}, 
	FEM(2, "Feminino") {
		@Override
		public String toString() {
			return getName();
		}
	}, 
	NAO_ESPICIFICADO(3, "Masculino") {
		@Override
		public String toString() {
			return getName();
		}
	};
	
	
	final int id;
	final String name;
	
	private Sexo(final int id, final String name) {
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}



	public String getName() {
		return name;
	}

	public static Sexo valueOfId(final int id) {
		return Arrays.asList(Sexo.values()).stream().filter(s -> s.id == id).findAny().orElse(NAO_ESPICIFICADO);
	}
	public static Sexo valueOfName(final String name) {
		return Arrays.asList(Sexo.values()).stream().filter(s -> s.name.equalsIgnoreCase(name)).findAny().orElse(NAO_ESPICIFICADO);
	}
}
