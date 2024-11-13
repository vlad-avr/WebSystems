import http from 'k6/http';
import { sleep } from 'k6';

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
                { duration: '30s', target: 50 }, // до 50 користувачів за 30 секунд
                { duration: '1m', target: 50 },  // тримаємо навантаження
                { duration: '30s', target: 0 },  // зменшення до нуля
            ],
            gracefulRampDown: '30s',
        },
    },
};

export default function () {
    http.get('http://server:8080/something');
    // Think time: пауза між запитами (випадкова пауза до 5 секунд)
    sleep(Math.random() * 5);
}