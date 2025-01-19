import axios from 'axios';
import {ElMessage} from 'element-plus';

const request = axios.create({
     baseURL: 'http://localhost:8080',
     timeout: 20000
})

//request 拦截器
request.interceptors.request.use(config => {
    // Do something before request is sent
    config.headers['Content-Type'] = 'application/json;charset=UTF-8'
    return config
}, error => {
    // Do something with request error
    return Promise.reject(error)
})


//response 拦截器
request.interceptors.response.use(
    response => {
        let data = response.data;
        if (typeof data === 'string'){
            data = data ? JSON.parse(data) : data
        }
        return data
    },
        error => {
            if (error.response.status === 404){
                ElMessage.error('未找到请求接口')
            } else if (error.response.status === 500){
                ElMessage.error('服务器内部错误')
            } else {
                console.error(error.message)
            }
            return Promise.reject(error)
        }
    )

export default request