package org.outofoffice.support.springboot;

import lombok.extern.slf4j.Slf4j;
import org.outofoffice.eidaprototype.lib.core.bean.EidaContext;
import org.outofoffice.eidaprototype.lib.core.socket.EidaDefaultSocketClient;
import org.outofoffice.eidaprototype.lib.core.ui.EidaEntity;
import org.outofoffice.eidaprototype.lib.core.ui.EidaRepository;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class EidaContextWrapper implements ApplicationContextAware, BeanFactoryPostProcessor {

    private BeanDefinitionRegistry registry;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("Aware Application Context: {}", applicationContext.getClass().getSimpleName());
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        log.info("Post process Bean Factory: {}", beanFactory.getClass().getSimpleName());
        registry = (BeanDefinitionRegistry) beanFactory;

        EidaContext.init(new EidaDefaultSocketClient());
        EidaContext.MAP.values().forEach(this::registerBean);
    }

    private void registerBean(EidaRepository<? extends EidaEntity<?>, ?> repository) {
        String beanName = toCamelCase(repository.getClass().getSimpleName());
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(repository.getClass());
        beanDefinition.setScope("singleton");
        beanDefinition.setAutowireCandidate(true);
        beanDefinition.setLazyInit(false);
        beanDefinition.setAbstract(false);
        registry.registerBeanDefinition(beanName, beanDefinition);
    }

    private String toCamelCase(String string) {
        String initial = string.substring(0, 1);
        return string.replaceFirst(initial, initial.toLowerCase());
    }

}
