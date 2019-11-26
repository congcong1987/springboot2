package com.gzzl.iservice.order.manifest;

import com.gzzl.domain.manifest.vo.ManifestResult;

public interface IManifestPortCodeService {
	public ManifestResult<String> delByPortCode(Integer id);
}
