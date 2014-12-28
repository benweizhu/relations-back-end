package me.zeph.relations.messages;

import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;
import java.util.Set;

public class KeysResourceBundleMessageSource extends ResourceBundleMessageSource {
	public Set<String> getKeys(String basename, Locale locale) {
		return getResourceBundle(basename, locale).keySet();
	}
}
