package in.cubestack.apps.android.lib.storm.criteria;

import in.cubestack.apps.android.lib.storm.core.TableInformation;

/**
 * A core Android SQLite ORM framrwork build for speed and raw execution.
 * Copyright (c) 2014  CubeStack. Version built for Flash Back..
 * <p/>
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 * <p/>
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 * <p/>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
 * OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN
 * NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE
 * USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
public class LikeRestriction implements Restriction {

	private String property; 
	private Object value;
	private boolean ignore;
	private TableInformation tableInfo;
	
	public LikeRestriction(String property, Object value, boolean ignoreCase, TableInformation information) {
		this.property = property;
		this.value = value;
		this.ignore = ignoreCase;
		this.tableInfo = information;
	}
	
	@Override
	public String toSqlString() {
		String column = tableInfo.getColumnName(property);
		if(value == null) {
			return column +" IS NULL ";
		}
		column = tableInfo.getAlias() + "." + column;
		if(ignore) {
			return  "lower(" + column + ") LIKE ? " ;
		} else {
			return column + " LIKE ? " ;
		}
	}

	@Override
	public boolean valueStored() {
		return true;
	}

	@Override
	public String[] values() {
		return new String[] {value != null ? value.toString(): null};
	}

}
