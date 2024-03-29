package ddc.bm.app;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.ws.rs.NameBinding;

public class SecurityAnnotation {
	@NameBinding
	@Retention(RetentionPolicy.RUNTIME)
	@Target(value = {ElementType.TYPE, ElementType.METHOD})
	public @interface SecureFeature {
		FeatureName[] value() default {};
	}
}
