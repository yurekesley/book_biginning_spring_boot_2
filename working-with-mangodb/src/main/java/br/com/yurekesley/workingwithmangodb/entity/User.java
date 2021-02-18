package br.com.yurekesley.workingwithmangodb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public @Data class User {

	private String id;
	private String name;
	private String email;
	private boolean disabled;
}
