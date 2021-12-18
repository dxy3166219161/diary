import axiosApi from "../util/axiosApi";

/**
 * 登录接口
 * @param param
 * @returns {AxiosPromise}
 */
export function login(param){
  return axiosApi({
    url:'/api/test/web/interface/access',
    method:'post',
    data:param
  })
}
