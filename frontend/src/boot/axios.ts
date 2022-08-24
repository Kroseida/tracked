import {boot} from 'quasar/wrappers';
import axios from 'axios';

export default boot(async () => {
  const configuration = (await axios.get('/configuration.json')).data;

  axios.defaults.baseURL = configuration.baseURL;
  axios.defaults.headers.common['Content-Type'] = 'application/json';
  axios.defaults.headers.common['Accept'] = 'application/json';
});
