/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.wol.service;

/**
 * <a href="SVNRevisionLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class SVNRevisionLocalServiceUtil {
	public static com.liferay.wol.model.SVNRevision addSVNRevision(
		com.liferay.wol.model.SVNRevision svnRevision)
		throws com.liferay.portal.SystemException {
		SVNRevisionLocalService svnRevisionLocalService = SVNRevisionLocalServiceFactory.getService();

		return svnRevisionLocalService.addSVNRevision(svnRevision);
	}

	public static void deleteSVNRevision(long svnRevisionId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		SVNRevisionLocalService svnRevisionLocalService = SVNRevisionLocalServiceFactory.getService();

		svnRevisionLocalService.deleteSVNRevision(svnRevisionId);
	}

	public static void deleteSVNRevision(
		com.liferay.wol.model.SVNRevision svnRevision)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		SVNRevisionLocalService svnRevisionLocalService = SVNRevisionLocalServiceFactory.getService();

		svnRevisionLocalService.deleteSVNRevision(svnRevision);
	}

	public static java.util.List<com.liferay.wol.model.SVNRevision> dynamicQuery(
		com.liferay.portal.kernel.dao.DynamicQueryInitializer queryInitializer)
		throws com.liferay.portal.SystemException {
		SVNRevisionLocalService svnRevisionLocalService = SVNRevisionLocalServiceFactory.getService();

		return svnRevisionLocalService.dynamicQuery(queryInitializer);
	}

	public static java.util.List<com.liferay.wol.model.SVNRevision> dynamicQuery(
		com.liferay.portal.kernel.dao.DynamicQueryInitializer queryInitializer,
		int begin, int end) throws com.liferay.portal.SystemException {
		SVNRevisionLocalService svnRevisionLocalService = SVNRevisionLocalServiceFactory.getService();

		return svnRevisionLocalService.dynamicQuery(queryInitializer, begin, end);
	}

	public static com.liferay.wol.model.SVNRevision updateSVNRevision(
		com.liferay.wol.model.SVNRevision svnRevision)
		throws com.liferay.portal.SystemException {
		SVNRevisionLocalService svnRevisionLocalService = SVNRevisionLocalServiceFactory.getService();

		return svnRevisionLocalService.updateSVNRevision(svnRevision);
	}

	public static com.liferay.wol.model.SVNRevision addSvnRevision(
		long svnRepositoryId, long revisionNumber, java.util.Date date,
		java.lang.String author, java.lang.String comments)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		SVNRevisionLocalService svnRevisionLocalService = SVNRevisionLocalServiceFactory.getService();

		return svnRevisionLocalService.addSvnRevision(svnRepositoryId,
			revisionNumber, date, author, comments);
	}
}