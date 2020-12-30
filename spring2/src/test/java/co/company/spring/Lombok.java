package co.company.spring;

import co.company.spring.controller.Member;

public class Lombok {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Member member;
		member = Member.builder().id("재영").name("재영").build();
		System.out.println(member);
	}

}
