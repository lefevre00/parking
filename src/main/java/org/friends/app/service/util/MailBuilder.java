/**
 * EcoParking v1.2
 * 
 * Application that allows management of shared parking 
 * among multiple users.
 * 
 * This file is copyrighted in LGPL License (LGPL)
 * 
 * Copyright (C) 2016 M. Lefevre, A. Tamditi, W. Verdeil
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 */
package org.friends.app.service.util;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import org.friends.app.service.MailException;
import org.springframework.util.StringUtils;

public class MailBuilder {

	private List<String> tos = new ArrayList<>();
	// TODO look somewhere at configuration
	private String from = "takemyplace@heroku.com";
	private String replyTo = "no-reply@heroku.com";
	private String subject;
	private String body;

	public static MailBuilder get() {
		return new MailBuilder();
	}

	/**
	 * Add a recipient
	 * 
	 * @param to
	 * @return itself so call can be chained
	 */
	public MailBuilder addTo(String to) {
		if (to != null)
			tos.add(to);
		return this;
	}

	/**
	 * Set the subject
	 * 
	 * @param text
	 * @return itself so call can be chained
	 */
	public MailBuilder setSubject(String text) {
		if (StringUtils.isEmpty(text))
			throw new InvalidParameterException("subject requiered");
		this.subject = text;
		return this;
	}

	/**
	 * Set the body
	 * 
	 * @param text
	 * @return itself so call can be chained
	 */
	public MailBuilder setText(String text) {
		if (StringUtils.isEmpty(text))
			throw new InvalidParameterException("text requiered");
		this.body = text;
		return this;
	}

	public Mail build() throws MailException {
		if (tos.isEmpty()) {
			throw new MailException("No recipient define");
		}
		if (StringUtils.isEmpty(from)) {
			throw new MailException("From must be defined");
		}
		if (StringUtils.isEmpty(replyTo)) {
			throw new MailException("ReplyTo must be defined");
		}
		if (StringUtils.isEmpty(subject)) {
			throw new MailException("Subject must be defined");
		}
		if (StringUtils.isEmpty(body)) {
			throw new MailException("Body must be defined");
		}

		Mail mail = new Mail();
		mail.setSubject(subject);
		mail.setBody(body);
		mail.setReplyTo(replyTo);
		mail.setFrom(from);
		mail.setTo(tos.toArray(new String[] {}));
		return mail;
	}
}
