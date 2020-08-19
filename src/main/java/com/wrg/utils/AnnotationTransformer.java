package com.wrg.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import com.wrg.abstestbase.RetryListener;

public class AnnotationTransformer implements IAnnotationTransformer {
	 
	 @Override
	 public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
	 annotation.setRetryAnalyzer(RetryListener.class);
	 }
	}
