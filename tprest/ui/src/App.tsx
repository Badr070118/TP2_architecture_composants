import { ChangeEvent, FormEvent, useCallback, useEffect, useState } from 'react';
import './App.css';

const API_BASE_URL = process.env.REACT_APP_API_BASE_URL ?? 'http://localhost:8080';

type Article = {
  id: number;
  description: string;
  price: number;
  quantity: number;
};

type FormState = {
  description: string;
  price: string;
  quantity: string;
};

const emptyForm: FormState = {
  description: '',
  price: '',
  quantity: ''
};

function App() {
  const [articles, setArticles] = useState<Article[]>([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const [message, setMessage] = useState<string | null>(null);
  const [form, setForm] = useState<FormState>(emptyForm);

  const loadArticles = useCallback(async () => {
    setLoading(true);
    setError(null);
    try {
      const response = await fetch(`${API_BASE_URL}/api/articles/all`);
      if (!response.ok) {
        throw new Error(`API error (${response.status})`);
      }
      const data: Article[] = await response.json();
      setArticles(data);
    } catch (err) {
      const message = err instanceof Error ? err.message : 'Une erreur inattendue est survenue';
      setError(message);
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => {
    loadArticles();
  }, [loadArticles]);

  const handleInputChange = (event: ChangeEvent<HTMLInputElement>) => {
    const { name, value } = event.target;
    setForm((previousValue: FormState) => ({
      ...previousValue,
      [name]: value
    }));
  };

  const handleSubmit = async (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    setError(null);
    setMessage(null);
    try {
      const payload = {
        description: form.description.trim(),
        price: Number(form.price),
        quantity: Number(form.quantity)
      };
      const response = await fetch(`${API_BASE_URL}/api/articles/create`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(payload)
      });
      if (!response.ok) {
        const text = await response.text();
        throw new Error(text || 'Impossible de créer l’article');
      }
      setMessage('Article créé avec succès');
      setForm({ ...emptyForm });
      await loadArticles();
    } catch (err) {
      const message = err instanceof Error ? err.message : 'Une erreur inattendue est survenue';
      setError(message);
    }
  };

  const handleDelete = async (id: number) => {
    setError(null);
    setMessage(null);
    try {
      const response = await fetch(`${API_BASE_URL}/api/articles/delete/${id}`, {
        method: 'DELETE'
      });
      if (!response.ok) {
        const text = await response.text();
        throw new Error(text || 'Impossible de supprimer l’article');
      }
      setMessage('Article supprimé');
      await loadArticles();
    } catch (err) {
      const message = err instanceof Error ? err.message : 'Une erreur inattendue est survenue';
      setError(message);
    }
  };

  return (
    <div className="App">
      <header>
        <div>
          <h1>Gestion des articles</h1>
          <p className="subtitle">Backend : {API_BASE_URL}</p>
        </div>
        <button type="button" onClick={loadArticles} disabled={loading}>
          Rafraîchir
        </button>
      </header>

      <section className="status">
        {loading && <span>Chargement...</span>}
        {error && <span className="error">{error}</span>}
        {message && <span className="message">{message}</span>}
      </section>

      <section className="grid">
        <form className="card" onSubmit={handleSubmit}>
          <h2>Nouvel article</h2>
          <label htmlFor="description">Description</label>
          <input
            id="description"
            name="description"
            value={form.description}
            onChange={handleInputChange}
            required
            placeholder="Ex: Clavier"
          />

          <label htmlFor="price">Prix</label>
          <input
            id="price"
            name="price"
            type="number"
            min="0"
            step="0.01"
            value={form.price}
            onChange={handleInputChange}
            required
          />

          <label htmlFor="quantity">Quantité</label>
          <input
            id="quantity"
            name="quantity"
            type="number"
            min="1"
            step="1"
            value={form.quantity}
            onChange={handleInputChange}
            required
          />

          <button type="submit" disabled={loading}>
            Enregistrer
          </button>
        </form>

        <div className="card">
          <h2>Inventaire</h2>
          <div className="table-wrapper">
            <table>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Description</th>
                  <th>Prix</th>
                  <th>Quantité</th>
                  <th aria-label="actions" />
                </tr>
              </thead>
              <tbody>
                {articles.length === 0 && (
                  <tr>
                    <td colSpan={5} className="empty">
                      Aucun article
                    </td>
                  </tr>
                )}
                {articles.map((article) => (
                  <tr key={article.id}>
                    <td>{article.id}</td>
                    <td>{article.description}</td>
                    <td>{article.price?.toFixed(2)} MAD</td>
                    <td>{article.quantity}</td>
                    <td>
                      <button
                        type="button"
                        className="danger"
                        onClick={() => handleDelete(article.id)}
                        disabled={loading}
                      >
                        Supprimer
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
      </section>
    </div>
  );
}

export default App;
