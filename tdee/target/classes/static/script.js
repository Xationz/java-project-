document.addEventListener('DOMContentLoaded', function() {
    const genderButtons = document.querySelectorAll('.gender-btn');
    const calculateBtn = document.getElementById('calculateBtn');
    const resultSection = document.getElementById('resultSection');
    const genderInput = document.getElementById('gender');
    const errorMsg = document.getElementById('errorMsg');
    
    // Gender toggle
    genderButtons.forEach(btn => {
        btn.addEventListener('click', (e) => {
            e.preventDefault();
            genderButtons.forEach(b => b.classList.remove('active'));
            btn.classList.add('active');
            genderInput.value = btn.dataset.gender;
        });
    });
    
    // Calculate TDEE
    calculateBtn.addEventListener('click', async (e) => {
        e.preventDefault();
        
        const formData = {
            gender: genderInput.value,
            age: parseInt(document.getElementById('age').value),
            weight: parseFloat(document.getElementById('weight').value),
            height: parseFloat(document.getElementById('height').value),
            activityLevel: document.getElementById('activity').value
        };
        
        try {
            errorMsg.classList.remove('show');
            
            const response = await fetch('/api/tdee', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(formData)
            });
            
            if (!response.ok) throw new Error('เกิดข้อผิดพลาด');
            
            const tdee = await response.json();
            updateResults(Math.round(tdee));
            resultSection.classList.remove('hidden');
            
        } catch (error) {
            errorMsg.textContent = '❌ ' + error.message;
            errorMsg.classList.add('show');
        }
    });
    
    function updateResults(tdee) {
        document.getElementById('resultValue').textContent = tdee.toLocaleString('th-TH');
        
        const deficits = [
            { kcal: tdee - 1000, pct: ((tdee - 1000) / tdee * 100).toFixed(0) },
            { kcal: tdee - 500, pct: ((tdee - 500) / tdee * 100).toFixed(0) },
            { kcal: tdee - 250, pct: ((tdee - 250) / tdee * 100).toFixed(0) },
            { kcal: tdee, pct: 100 },
            { kcal: tdee + 250, pct: ((tdee + 250) / tdee * 100).toFixed(0) },
            { kcal: tdee + 500, pct: ((tdee + 500) / tdee * 100).toFixed(0) },
            { kcal: tdee + 1000, pct: ((tdee + 1000) / tdee * 100).toFixed(0) }
        ];
        
        const ids = ['kcal1', 'kcal2', 'kcal3', 'kcal4', 'kcal5', 'kcal6', 'kcal7'];
        const pctIds = ['pct1', 'pct2', 'pct3', 'pct4', 'pct5', 'pct6', 'pct7'];
        
        deficits.forEach((item, index) => {
            document.getElementById(ids[index]).textContent = Math.round(item.kcal) + ' แคลอรี่/วัน';
            document.getElementById(pctIds[index]).textContent = item.pct + '%';
        });
    }
    
    // Auto calculate on load
    calculateBtn.click();
});
