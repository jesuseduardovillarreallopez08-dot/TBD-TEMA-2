import express from 'express';
import dotenv from 'dotenv';
import productosRouter from './routes/productos.js';

dotenv.config();

const app = express();
const PORT = process.env.PORT || 3000;

app.use(express.json());
app.use('/api/productos', productosRouter);

app.listen(PORT, () => {
  console.log(`Servidor de la cafetería corriendo en http://localhost:${PORT}`);
});