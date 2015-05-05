/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.drv.dsrv.kernpruefung.basis.utils;

/**
 * Builds a string from constituent parts providing a more flexible and powerful
 * API than StringBuffer.
 * <p>
 * The main differences from StringBuffer/StringBuilder are:
 * <ul>
 * <li>Not synchronized</li>
 * <li>Not final</li>
 * <li>Subclasses have direct access to character array</li>
 * <li>Additional methods
 * <ul>
 * <li>appendWithSeparators - adds an array of values, with a separator</li>
 * <li>appendPadding - adds a length padding characters</li>
 * <li>appendFixedLength - adds a fixed width field to the builder</li>
 * <li>toCharArray/getChars - simpler ways to get a range of the character array
 * </li>
 * <li>delete - delete char or string</li>
 * <li>replace - search and replace for a char or string</li>
 * <li>leftString/rightString/midString - substring without exceptions</li>
 * <li>contains - whether the builder contains a char or string</li>
 * <li>size/clear/isEmpty - collections style API methods</li>
 * </ul>
 * </li>
 * </ul>
 * <li>Views
 * <ul>
 * <li>asTokenizer - uses the internal buffer as the source of a StrTokenizer</li>
 * <li>asReader - uses the internal buffer as the source of a Reader</li>
 * <li>asWriter - allows a Writer to write directly to the internal buffer</li>
 * </ul>
 * </li> < /ul>
 * <p>
 * The aim has been to provide an API that mimics very closely what StringBuffer
 * provides, but with additional methods. It should be noted that some edge
 * cases, with invalid indices or null input, have been altered - see individual
 * methods. The biggest of these changes is that by default, null will not
 * output the text 'null'. This can be controlled by a property,
 * {@link #setNullText(String)}.
 * <p>
 * Prior to 3.0, this class implemented Cloneable but did not implement the
 * clone method so could not be used. From 3.0 onwards it no longer implements
 * the interface.
 * 
 * @author Apache Software Foundation
 * @since 2.2
 * @version $Id: StrBuilder.java,v 1.1 2014/12/04 11:32:52 V990341 Exp $
 */
public class StrBuilder implements Cloneable {

	/**
	 * The extra capacity for new builders.
	 */
	static final int CAPACITY = 32;

	/**
	 * Required for serialization support.
	 * 
	 * @see java.io.Serializable
	 */
	private static final long serialVersionUID = 7628716375283629643L;

	/** Internal data storage. */
	private char[] buffer;
	/** Current size of the buffer. */
	private int size;
	/** The null text. */
	private String nullText;

	// -----------------------------------------------------------------------
	/**
	 * Constructor that creates an empty builder initial capacity 32 characters.
	 */
	public StrBuilder() {
		this(CAPACITY);
	}

	public StrBuilder(int initialCapacity) {
		super();
		if (initialCapacity <= 0) {
			buffer = new char[CAPACITY];
		} else {
			buffer = new char[initialCapacity];
		}
	}

	/**
	 * Gets the text to be appended when null is added.
	 * 
	 * @return the null text, null means no append
	 */
	public String getNullText() {
		return nullText;
	}

	/**
	 * Checks the capacity and ensures that it is at least the size specified.
	 * 
	 * @param capacity
	 *            the capacity to ensure
	 * @return this, to enable chaining
	 */
	public StrBuilder ensureCapacity(int capacity) {
		if (capacity > buffer.length) {
			final char[] old = buffer;
			buffer = new char[capacity * 2];
			System.arraycopy(old, 0, buffer, 0, size);
		}
		return this;
	}

	/**
	 * Appends an object to the builder padding on the right to a fixed length.
	 * The <code>toString</code> of the object is used. If the object is larger
	 * than the length, the right hand side is lost. If the object is null, null
	 * text value is used.
	 * 
	 * @param obj
	 *            the object to append, null uses null text
	 * @param width
	 *            the fixed field width, zero or negative has no effect
	 * @param padChar
	 *            the pad character to use
	 * @return this, to enable chaining
	 */
	public StrBuilder appendFixedWidthPadRight(Object obj, int width, char padChar) {
		if (width > 0) {
			ensureCapacity(size + width);
			String str = (obj == null ? getNullText() : obj.toString());
			if (str == null) {
				str = "";
			}
			final int strLen = str.length();
			if (strLen >= width) {
				str.getChars(0, width, buffer, size);
			} else {
				final int padLen = width - strLen;
				str.getChars(0, strLen, buffer, size);
				for (int i = 0; i < padLen; i++) {
					buffer[size + strLen + i] = padChar;
				}
			}
			size += width;
		}
		return this;
	}

	/**
	 * Appends an object to the builder padding on the right to a fixed length.
	 * The <code>String.valueOf</code> of the <code>int</code> value is used. If
	 * the object is larger than the length, the right hand side is lost.
	 * 
	 * @param value
	 *            the value to append
	 * @param width
	 *            the fixed field width, zero or negative has no effect
	 * @param padChar
	 *            the pad character to use
	 * @return this, to enable chaining
	 */
	public StrBuilder appendFixedWidthPadRight(int value, int width, char padChar) {
		return appendFixedWidthPadRight(String.valueOf(value), width, padChar);
	}

	/**
	 * Gets a String version of the string builder, creating a new instance each
	 * time the method is called.
	 * <p>
	 * Note that unlike StringBuffer, the string version returned is independent
	 * of the string builder.
	 * 
	 * @return the builder as a String
	 */
	@Override
	public String toString() {
		return new String(buffer, 0, size);
	}

}