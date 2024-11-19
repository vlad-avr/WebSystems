import http from 'k6/http';
import { sleep, check } from 'k6';

export let options = {
    scenarios: {
        constant_load_test: {
            executor: 'constant-vus',
            vus: 10,
            duration: '1m',
        },
        ramping_load_test: {
            executor: 'ramping-vus',
            startVUs: 0,
            stages: [
                { duration: '30s', target: 50 }, // Ramp up to 50 users in 30 seconds
                { duration: '1m', target: 50 },  // Hold at 50 users for 1 minute
                { duration: '30s', target: 0 },  // Ramp down to 0 users in 30 seconds
            ],
            gracefulRampDown: '30s',
        },
        constant_arrival_rate_test: {
            executor: 'constant-arrival-rate',
            rate: 20, // 20 iterations per second
            timeUnit: '1s', // time unit for the rate
            duration: '1m', // run the test for 1 minute
            preAllocatedVUs: 50, // number of VUs to be pre-allocated
            maxVUs: 100, // max number of VUs that can be used
        },
    },
};

export default function () {
    // let res = http.get('http://server:8080/something');
    // check(res, { 'successful get' : (r) => r.status === 200 });
    // // Think time: random pause between requests (up to 5 seconds)
    // sleep(Math.random() * 5);
    let productId = "12345"; // Використовуйте будь-який ID для продукту
    let res = http.get(`http://localhost:8080/products/${productId}`);
    
    // Перевірка, чи був запит успішним
    check(res, { 'successful get' : (r) => r.status === 200 });
    
    // Вивести час відповіді, щоб оцінити продуктивність
    console.log(`Response time: ${res.timings.duration} ms`);

    // Тестування кешування: Виконуємо ще один запит до того ж продукту
    let cachedRes = http.get(`http://localhost:8080/products/${productId}`);
    check(cachedRes, { 'successful get from cache' : (r) => r.status === 200 });
    console.log(`Response time for cached request: ${cachedRes.timings.duration} ms`);
}
