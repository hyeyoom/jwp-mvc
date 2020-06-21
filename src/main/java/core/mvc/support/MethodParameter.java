package core.mvc.support;

import javax.annotation.Nullable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Objects;

public class MethodParameter {

    private final Method method;
    private final NamedParameter namedParameter;

    public MethodParameter(Method method, NamedParameter namedParameter) {
        this.method = method;
        this.namedParameter = namedParameter;
    }

    public String getParameterName() {
        return namedParameter.getParameterName();
    }

    public Class<?> getParameterType() {
        return namedParameter.getParameterType();
    }

    public boolean isAnnotated() {
        return namedParameter.isAnnotated();
    }

    @SuppressWarnings("unchecked")
    public @Nullable
    <A extends Annotation> A getParameterAnnotation(Class<A> annotation) {
        Objects.requireNonNull(annotation, "Given annotation is null.");
        for (Annotation parameterAnnotation : namedParameter.getParameterAnnotations()) {
            if (annotation.isInstance(parameterAnnotation)) {
                return (A) parameterAnnotation;
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public @Nullable
    <A extends Annotation> A getMethodAnnotation(Class<A> annotation) {
        Objects.requireNonNull(annotation, "Given annotation is null.");
        return method.getAnnotation(annotation);
    }
}
