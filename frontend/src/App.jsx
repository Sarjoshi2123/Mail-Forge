import { useState } from 'react'
import './App.css'
import { Box, Container, Menu, MenuItem, TextField, Typography, FormControl, InputLabel, Select, Button, CircularProgress } from '@mui/material';
import axios from 'axios';

function App() {

  const [emailContent, setEmailContent] = useState('');
  const [tone, setTone] = useState('');
  const [generatedReply, setGeneratedReply] = useState('');
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');

  const handleSubmit = async () => {
    setLoading(true);
    setError('');
    try {
      const API_BASE_URL = import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080';
      const response = await axios.post(`${API_BASE_URL}/api/email/generate`, {
        emailContent,
        tone
      })
      setGeneratedReply(typeof response.data == 'string' ? response.data : JSON.stringify(response.data));
    } catch (error) {
      setError("Failed to generate email reply Please try again.")
      console.error(error);
    } finally {
      setLoading(false);
    }
  };

  return (
    <Container maxWidth="md" sx={{ py: 4 }}>
      <Typography
        variant="h4"
        component="h1"
        gutterBottom
        sx={{ fontWeight: 800 }}
      >
        Mail<span style={{ color: "#7f5af0" }}>Forge</span>
      </Typography>

      <Typography
        variant="subtitle1"
        sx={{ textAlign: "center", mb: 3 }}
      >
        AI-powered email crafting made effortless
      </Typography>

      <Box sx={{ mx: 3 }}>
        <TextField
          fullWidth
          multiline
          rows={6}
          variant='outlined'
          label='Origional Email Content'
          value={emailContent || ''}
          onChange={(e) => setEmailContent(e.target.value)} />

        <FormControl fullWidth sx={{ mt: 2 }}>
          <InputLabel>Tone (Optional)</InputLabel>
          <Select
            value={tone || ''}
            label={"Tone (Optional)"}
            onChange={(e) => setTone(e.target.value)}>
            <MenuItem value="">None</MenuItem>
            <MenuItem value="professional">Professional</MenuItem>
            <MenuItem value="casual">Casual</MenuItem>
            <MenuItem value="friendly">Friendly</MenuItem>
          </Select>
        </FormControl>

        <Button
          sx={{ mt: 2 }}
          variant='contained'
          onClick={handleSubmit}
          disabled={!emailContent || loading}
          fullWidth>
          {loading ? <CircularProgress size={24} /> : "Generated Reply"}
        </Button>
      </Box>

      {error && (
        <Typography color='error' sx={{ mb: 2 }}>
          {error}
        </Typography>
      )}

      {generatedReply && (
        <Box sx={{ mt: 3 }}>
          <Typography variant='h6' gutterBottom>
            Generated Reply :
          </Typography>

          <TextField
            fullWidth
            multiline
            rows={6}
            variant='outlined'
            value={generatedReply || ''}
            inputProps={{ readOnly: true }} />

          <Button variant='outlined' sx={{ mt: 2 }} onClick={() => navigator.clipboard.writeText(generatedReply)}>
            Copy to ClipBoard
          </Button>
        </Box>
      )}

    </Container>
  )
}

export default App 
